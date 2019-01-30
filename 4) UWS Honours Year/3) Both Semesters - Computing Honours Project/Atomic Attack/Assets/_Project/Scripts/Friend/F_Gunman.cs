using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class F_Gunman : AI_Friend {

    // Child Class F_Gunman inheriting from AI_Friend.
    Animator Animator;

    [Space( 10), Header("[^ Child: AI_Friend ]")]
    [Space(-10), Header("[^ Child:   F_Gunman ] Damage")]
    [SerializeField] protected float AttackRate = 0.75f;
    protected float NextAttackTime = 0;

    [Space( 10), Header("[^ Child:   F_Gunman ] Weapon")]
    [Space(-10), Header("3.0f = Projectile Damage")]
    [SerializeField] protected GameObject Projectile;
    [SerializeField] protected Transform FireLocation;

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

    protected virtual void Shoot()
    {   // Instantiating Bullet prefab.
        GameObject Bullet = Instantiate(Projectile, FireLocation.position, FireLocation.rotation);
        Bullet bullet = Bullet.GetComponent<Bullet>();
        if (bullet != null) { bullet.Seek(Target); } // Using Bullet's script Seek method.
        // Add Bullet's Damage on the prefab. AttackDamage value here is just reference.
    }

    void OnCollisionEnter2D(Collision2D collision)
    {
        if (collision.gameObject.tag == "Ground") { Grounded = true; } else { }
        // not detecting when the sprite has left the ground... else function doesn't work
    }
}