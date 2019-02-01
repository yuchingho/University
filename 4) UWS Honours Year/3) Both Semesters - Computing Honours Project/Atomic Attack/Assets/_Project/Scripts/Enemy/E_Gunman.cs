using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class E_Gunman : AI_Enemy {

    // Child Class E_Gunman inheriting from AI_Enemy.
    Animator Animator;

    [Space( 10), Header("[^ Child: AI_Enemy ]")]
    [Space(-10), Header("[^ Child:   E_Gunman ]")]
    [SerializeField] protected float AttackRate = 0.75f;
    protected float NextAttackTime = 0;
    #pragma warning disable
    [SerializeField] string AttackDamage = "2 [RO] Add to Prefab";

    [Space(10), Header("[^ Child:   E_Gunman ] Weapon")]
    [SerializeField] protected GameObject Projectile;
    [SerializeField] protected Transform FireLocation;

    [Space(10), Header("[^ Child:   E_Gunman ] Affected By")]
    public bool Stunned;
    public bool Blinded;
    public bool OnCastle;

    void Reset()
    {
        MovementSpeed = 1f;
        LookRadius = 4f;
        AttackRadius = LookRadius;
    }

    protected override void Start()
    {
        base.Start();
        Animator = GetComponent<Animator>();
    }

    protected override void LookAtTarget()
    {
        if (OnCastle == true)
        {   // When on Castle, different Angle algorithm so will face Target correctly.
            Vector3 dir = Target.position - transform.position;
            float Angle = Mathf.Atan2(dir.z, dir.x) * Mathf.Rad2Deg;
            if (Angle <= 160) { MovementDirection = -1; }
            if (Angle >= 170) { MovementDirection = 1; Angle -= 180; }
            transform.rotation = Quaternion.AngleAxis(Angle, Vector3.left);
        }
        else { base.LookAtTarget(); }
    }

    protected override void Update()
    {
        if (OnCastle == true)
        {
            MovementSpeed = 0;
            base.Update();
        }
        else
        {
            base.Update();
        }
    }

    protected override void Movement()
    {
        if (Grounded == true && GrabbedByMouse == false)
        {
            base.Movement();
            // Calculate the distance inbetween Target and Self. Will stop if inside AttackRadius.
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
        }
        // For when chucked in air, only start coroutine after enemy has hit the ground.
        else if (Grounded == true && GrabbedByMouse == true) { StartCoroutine(HitTheGround()); }
    }

    protected override void PlayAnimationAttack()
    {
        if (Time.time > NextAttackTime)
        {
            Animator.Play("Attack", -1, 0);
            NextAttackTime = Time.time + AttackRate;
            Shoot();
        }
    }

    protected override void PlayAnimationDeath()
    {
        Animator.Play("Die");
        Destroy(gameObject, 1f);
        return;
        // Add to score or collateral damage score
    }

    void Shoot()
    {   // Instantiating Bullet prefab.
        GameObject Bullet = Instantiate(Projectile, FireLocation.position, FireLocation.rotation);
        Bullet bullet = Bullet.GetComponent<Bullet>();
        if (bullet != null) { bullet.Seek(Target); } // Using Bullet's script Seek method.
        // Add Bullet's Damage on the prefab. AttackDamage value here is just reference.
    }

    void OnMouseDown()
    {
        GrabbedByMouse = true;
        MovementSpeed = 0;
    }

    void OnMouseDrag()
    {
        if (Input.mousePosition.y >= 130)
        {
            GrabbedByMouse = true;
            Grounded = false;
            gameObject.layer = 10;  // Mouse Layer.
            Vector3 newPosition = new Vector3(Input.mousePosition.x, Input.mousePosition.y, 10.0f);
            transform.position = Camera.main.ScreenToWorldPoint(newPosition);

            // need to add force so "throws" like a ball.
            // add fall damage
        }
    }

    void OnMouseUp()
    {   // Health System here because will double-register otherwise.
        GetComponent<HealthSystem>().DamageTaken(1);
        gameObject.layer = 8;     // Enemy Layer.
    }

    IEnumerator HitTheGround()
    {
        Animator.Play("Die");
        yield return new WaitForSeconds(1f);
        GrabbedByMouse = false;
    }

    void OnCollisionEnter2D(Collision2D collision)
    {
        if (collision.gameObject.tag == "Ground")
        {
            OnCastle = false;
            Grounded = true;
            LookRadius = 4f;
            AttackRadius = 4f;
        }

        else if (collision.gameObject.tag == "Castle")
        {
            OnCastle = true;
            Grounded = true;
            LookRadius = 10f;
            AttackRadius = 10f;
        }
    }
}