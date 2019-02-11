using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public abstract class AI_Human : MonoBehaviour {

    // Parent Class for Inheritance.
    Rigidbody2D Rigidbody2D;
    Animator Animator;
    protected HealthSystem HealthSystem;

    [Space(-10), Header("[ Parent: AI_Human ] Movement")]
    public int CostValue;
    public int ScoreValue;

    [Space( 10), Header("[ Parent: AI_Human ] Movement")]
    [SerializeField] protected float MovementSpeed;
    protected float MovementSpeedInitial;
    protected int MovementDirection;
    public bool Grounded;
    protected Vector3 PreviousGrabbedPosition;
    [SerializeField] protected bool GrabbedByMouse;
    [SerializeField] protected bool OnCastle;

    [Space( 10), Header("[ Parent: AI_Human ] Affected By")]
    public bool Suffocate;  // For 07_Nitrogen + 15_Phosphorus. (DoT).
    public bool Poisoned;   // For 09_Flourine + 17_Chlorine.   (DoT).
    public bool Burned;     // For 18_Argon, flamethrower guy.  (DoT).
    public bool Blinded;    // For Enemies.
    public bool Stunned;    // For Enemies.
    [SerializeField] protected GameObject EffectSuffocate;  // Text Effect.
    [SerializeField] protected GameObject EffectPoisoned;   // Text Effect.
    [SerializeField] protected GameObject EffectBurned;     // Order in layer = 1.
    [SerializeField] protected GameObject EffectBlinded;    // Text Effect.
    [SerializeField] protected GameObject EffectStunned;    // Order in layer = 1.

    [Space( 10), Header("----------------- Target ----------------")]
    public Transform Target;
    [SerializeField] protected float TargetHealth;
    public Transform FinalTarget;

    [Space( 10), Header("----------------- Stats -----------------")]
    [SerializeField] protected float LookRadius;
    [SerializeField] protected float AttackRadius;
    [SerializeField] protected float AttackRate;
    protected float NextAttackTime = 0;

    protected virtual void Start()
    {
        Rigidbody2D = GetComponent<Rigidbody2D>();
        Animator = GetComponent<Animator>();
        HealthSystem = GetComponent<HealthSystem>();
        MovementSpeedInitial = MovementSpeed;
    }

    // Child Classes Enemy.cs and Friend.cs have InvokeRepeating UpdateTarget() every 0.25f.
    // Is basically another "Update" Method, which if has a Target, will go to LookatTarget().
    protected virtual void Update()
    {   // If out of bounds, destroy GameObject and not add to score, etc.
        if (transform.position.x<=-15 || transform.position.x>=15 || transform.position.y<=-7) { Destroy(gameObject); }
        else if (Grounded == true && HealthSystem.Deceased == true) { PlayAnimationDeath(); }
        else if (Grounded == true && GrabbedByMouse == false) { Movement(); }
    }

    protected virtual void LookAtTarget()
    {   // Sprites flipping to look at its Target.
        if (Target != null)
        {
            Vector3 dir = Target.position - transform.position;
            float Angle = Mathf.Atan2(dir.y, dir.x) * Mathf.Rad2Deg;
            if (Angle <= 160) { MovementDirection =-1; }
            if (Angle >= 170) { MovementDirection = 1; Angle -= 180; }
            transform.rotation = Quaternion.AngleAxis(Angle, Vector3.forward);
        }
    }

    protected virtual void Movement()
    {
        Rigidbody2D.velocity = new Vector2(MovementSpeed * -MovementDirection, 0);
        transform.localScale = new Vector2(0.3f * MovementDirection, 0.3f);
        
        if (Stunned == true) { StartCoroutine(StatusStunned()); }
        else {
        if (Target != null)
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

    protected virtual void PlayAnimationAttack()
    {
        if (Time.time > NextAttackTime)
        {    // Animator.Play(state, layer, normalizedTime);
             // Need the other 2 overloads, otherwise won't repeat every AttackRate.
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
        // Add to points and score or collateral damage score
    }

    // Added two new Layers - "Enemy" and "Friend".
    // Enemies and Friends can overlap.
    // The two layers colliding have been disabled.
    // Edit > Project Settings > Physics 2D.
    // --------------------------------------------------------------------------
    // Same as "virtual void", but has to be called in Child classes.
    // Since added "abstract void" here, have to add "abstract" at start of class.
    protected abstract void OnDrawGizmos();

    protected virtual void Shoot() { }

    protected virtual IEnumerator StatusSuffocate()
    {
        yield return new WaitForSeconds(1f);
        if (Suffocate == true) { EffectSuffocate.SetActive(true); }
        else { EffectSuffocate.SetActive(false); }
    }

    protected virtual IEnumerator StatusPoisoned()
    {
        yield return new WaitForSeconds(1f);

        if (Poisoned == true) { EffectPoisoned.SetActive(true); }
        else { EffectPoisoned.SetActive(false); }
    }

    protected virtual IEnumerator StatusBurned()
    {
        yield return new WaitForSeconds(1f);

        if (Burned == true) { EffectBurned.SetActive(true); }
        else { EffectBurned.SetActive(false); }
    }

    // ------- Only for Enemies -------
    protected virtual IEnumerator StatusBlinded()
    {
        yield return new WaitForSeconds(1f);

        if (Blinded == true) { EffectBlinded.SetActive(true); }
        else { EffectBlinded.SetActive(false); }
    }

    protected virtual IEnumerator StatusStunned()
    {
        MovementSpeed = 0;
        gameObject.GetComponent<Animator>().Play("Stunned");
        yield return new WaitForSeconds(1f);
        Stunned = false;
    }
}