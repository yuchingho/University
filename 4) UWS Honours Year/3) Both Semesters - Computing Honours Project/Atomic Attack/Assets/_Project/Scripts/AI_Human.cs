using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class AI_Human : MonoBehaviour {

    // Parent Class for Inheritance.
    protected SpriteRenderer SpriteRenderer;
    protected Rigidbody2D Rigidbody2D;
    protected Animator Animator;
    protected AudioSource AudioSource;
    //protected ManagerAudio ManagerAudio;
    protected HealthSystem HealthSystem;
    protected ManagerGame ManagerGame;

    [Space(-10), Header("[ Parent: AI_Human ] Movement")]
    public float MovementSpeed;
    public bool Grounded;
    public bool Unshakeable;
    public bool GrabbedByMouse;
    [SerializeField] protected bool OnTheCastle;
    protected Vector3 PreviousGrabbedPosition;
    protected float MovementSpeedInitial;
    [HideInInspector] public int MovementDirection;

    [Space( 10), Header("[ Parent: AI_Human ] Affected By")]
    public bool Stunned;    // For Enemies.
    public bool Blinded;    // For Enemies.
    public bool Frozen;     // For 07_Nitrogen.
    public bool Burned;     // For 15_Phosphorus + 18_Argon.    (DoT).
    public bool RunAway;    // For 16_Sulphur.
    [SerializeField] protected GameObject EffectStunned;    // Order in layer = 1.
    [SerializeField] protected GameObject EffectBlinded;    // Order in layer = 1.
    [SerializeField] protected GameObject EffectBurned;     // Order in layer = 1.
    [SerializeField] protected GameObject GasMask;          // Order in layer = 1.


    [Space( 10), Header("----------------- Target ----------------")]
    public Transform Target;
    [SerializeField] protected float TargetHealth;
    public Transform FinalTarget;

    [Space( 10), Header("----------------- Stats -----------------")]
    [SerializeField] protected float AttackRate;
    [SerializeField] protected float LookRadius;
    [SerializeField] protected float AttackRadius;
    protected float NextAttackTime = 0;
    [SerializeField] protected AudioClip A_Attack;


    protected virtual void Start()
    {
        SpriteRenderer = GetComponent<SpriteRenderer>();
        Rigidbody2D = GetComponent<Rigidbody2D>();
        Animator = GetComponent<Animator>();
        AudioSource = GetComponent<AudioSource>();
        HealthSystem = GetComponent<HealthSystem>();
        ManagerGame = GameObject.Find("Manager Game").GetComponent<ManagerGame>();
        MovementSpeedInitial = MovementSpeed;
    }

    // Child Classes "AI_Enemy" and "AI_Friend" have InvokeRepeating UpdateTarget() every 0.25f.
    // ^ basically another "Update" Method, which if has a Target, will go to LookatTarget().
    protected virtual void Update()
    {   // If out of bounds, destroy GameObject and not add to score, etc.
        if (transform.position.x <= -15 || transform.position.x >= 15 || transform.position.y <= -7)
        { Destroy(gameObject); }
        else if ((Grounded == true && HealthSystem.Deceased == true) || HealthSystem.Health <= -350)
        {
            // A lot of the time, in "_Explode.cs" Guy slides across the ground.
            // Grounded == true doesn't properly register, so will stand there till Dead.
            PlayAnimationDeath();
            // Adding HealthSystem.CounterValues to ManagerGame.CurrentValues.
            // Killing Enemies earn gold, while Friends dying don't.
            // Earn Score when Enemies die, and when Friends are Button Spawned.
            if (HealthSystem.CounterAdded == false)
            {   
                ManagerGame.CurrentScore       += HealthSystem.CounterScore;
                ManagerGame.CurrentGold        += HealthSystem.GoldEarned;
                ManagerGame.CurrentE_Gunmen    += HealthSystem.CounterE_Gunmen;
                ManagerGame.CopyE_Gunmen       += HealthSystem.CounterE_Gunmen;
                ManagerGame.CurrentE_Swordsmen += HealthSystem.CounterE_Swordsmen;
                ManagerGame.CopyE_Swordsmen    += HealthSystem.CounterE_Swordsmen;
                ManagerGame.TotalFriends       += HealthSystem.CounterFriends;
                HealthSystem.CounterAdded = true;
            }
        }
        else if (Grounded == true && GrabbedByMouse == false)
        {   // Status effects initialized.
            StartCoroutine(StatusBlinded());
            StartCoroutine(StatusBurned());
            StatusSmell();
            Movement();
        }
    }

    protected virtual void LookAtTarget()
    {   // Sprites flipping to look at its Target.
        if (Target != null)
        {
            Vector3 dir = Target.position - transform.position;
            float Angle = Mathf.Atan2(dir.y, dir.x) * Mathf.Rad2Deg;
            if (Angle <=  160) { MovementDirection =-1; }
            if (Angle >=  170) { MovementDirection = 1; Angle -= 180; }
            if (Angle <= -170) { MovementDirection = 1; Angle += 180; }
            transform.rotation = Quaternion.AngleAxis(Angle, Vector3.forward);
        }
    }

    protected virtual void Movement()
    {
        Rigidbody2D.velocity = new Vector2(MovementSpeed * -MovementDirection, 0);
        transform.localScale = new Vector2(0.3f * MovementDirection, 0.3f);
        
        if (Stunned == true) { StartCoroutine(StatusStunned()); }
        else { if (Target != null)
        {   // Calculate the distance inbetween Target and Self. Will stop if inside AttackRadius.
            float AttackRange = Vector2.Distance(transform.position, Target.transform.position);
            if (AttackRange <= AttackRadius)
            {   // If inside AttackRadius, will start damaging enemy!
                MovementSpeed = 0;
                PlayAnimationAttack();
            }
            else
            {
                MovementSpeed = MovementSpeedInitial;
                Animator.Play("Run");
            }
        } }
    }

    protected virtual void Shoot() { }
    protected virtual void PlayAnimationAttack()
    {
        if (Time.time > NextAttackTime)
        {    // Animator.Play(state, layer, normalizedTime);
             // Need the other 2 overloads, otherwise won't repeat every AttackRate.
            AudioSource.PlayOneShot(A_Attack, 0.4f);
            Animator.Play("Attack", -1, 0);
            NextAttackTime = Time.time + AttackRate;
            Shoot(); // Shoot is overridden in Gunmen.
        }
    }

    protected virtual void PlayAnimationDeath()
    {
        Animator.Play("Die");
        Destroy(gameObject, 1f);
        return;
    }

    // Use if GrabbedByMouse or thrown in air by Explosion.
    protected virtual IEnumerator HitTheGround()
    {
        Animator.Play("Die");
        yield return new WaitForSeconds(1f);
        GrabbedByMouse = false;
        MovementSpeed = MovementSpeedInitial;
    }

    #region Layers and Abstract void
    // Added two new Layers - "Enemy" and "Friend".
    // Enemies and Friends can overlap.
    // The two layers colliding have been disabled.
    // Edit > Project Settings > Physics 2D.
    // --------------------------------------------------------------------------
    // Same as "virtual void", but has to be called in Child classes.
    // Since added "abstract void" here, have to add "abstract" at start of class.
    #endregion
    #region Status effects
    protected virtual IEnumerator StatusStunned()
    {   // Enemy Duration = 1s.
        MovementSpeed = 0;
        Animator.Play("Stunned");
        yield return new WaitForSeconds(1);
        Stunned = false;
    }

    protected virtual IEnumerator StatusBlinded()
    {   // Enemy Duration = 2s.
        if (Blinded == true)
        {
            EffectBlinded.SetActive(true);
            yield return new WaitForSeconds(2);
            EffectBlinded.SetActive(false);
            Blinded = false;
        }
    }

    protected virtual IEnumerator StatusBurned()
    {   // All Duration = 5s.
        if (Burned == true)
        {
            EffectBurned.SetActive(true);
            yield return new WaitForSeconds(5);
            EffectBurned.SetActive(false);
            Burned = false;
        }
    }

    protected virtual void StatusSmell() // Not removing gas-masks.
    { if (RunAway == true) { GasMask.SetActive(true); } }
    #endregion
}