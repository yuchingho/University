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
    [SerializeField] string AttackDamage = "2 (Read Only)";

    [Space(10), Header("[^ Child:   E_Gunman ] Weapon")]
    [SerializeField] protected GameObject Projectile;
    [SerializeField] protected Transform FireLocation;

    [Space(10), Header("[^ Child:   E_Gunman ] Affected By")]
    public bool GrabbedByMouse;
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

    protected override void Update()
    {
        base.Update();  // If not on Castle, run as normal. If so, range = 10f.
        if (OnCastle == true)
        {
            MovementSpeed = 0f;
            LookRadius = 10f;
            AttackRadius = 10f;
        }
    }

    protected override void LookAtTarget()
    {
        if (OnCastle == false) { base.LookAtTarget(); }
        else
        {   // When on Castle, different Angle algorithm so will face Target correctly.
            Vector3 dir = Target.position - transform.position;
            float Angle = Mathf.Atan2(dir.z, dir.x) * Mathf.Rad2Deg;
            if (Angle <= 160) { MovementDirection = -1; }
            if (Angle >= 170) { MovementDirection = 1; Angle -= 180; }
            transform.rotation = Quaternion.AngleAxis(Angle, Vector3.left);
        }
    }

    protected override void Movement()
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
        // when tapped, stop moving/attacking, play die animation, then get up.
        // coroutine?
    }

    void OnMouseDrag()
    {
        //Destroy(this.gameObject);
        //Animator.Play("Die");
        //Animator.enabled = false;

        // when dragged, stop moving/attacking, don't play die animation, and can be dragged about and in air
    }

    void OnCollisionEnter2D(Collision2D collision)
    {
        if (collision.gameObject.tag == "Ground") { Grounded = true; }
        //Destroy(GetComponent<SpawnOnCastle>());
        else if (collision.gameObject.tag == "Castle") { OnCastle = true; }
    }
}