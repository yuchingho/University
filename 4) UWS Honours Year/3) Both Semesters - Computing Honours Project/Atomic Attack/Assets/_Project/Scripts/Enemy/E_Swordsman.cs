using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class E_Swordsman : AI_Enemy {

    // Child Class E_Swordsman inheriting from AI_Enemy.
    Animator Animator;

    [Space( 10), Header("[^ Child: AI_Enemy ]")]
    [Space(-10), Header("[^ Child:   E_Swordsman ] Damage")]
    [SerializeField] protected float AttackRate = 1f;
    protected float NextAttackTime = 0;
    [SerializeField] protected int AttackDamage = 1;

    [Space( 10), Header("[^ Child:   E_Swordsman ] Affected By")]
    public bool Stunned;
    public bool Blinded;
    public bool Burned;

    void Reset()
    {
        MovementSpeed = 1.2f;
        LookRadius = 3f;
        AttackRadius = 0.85f;
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
        {   // Playing Animation will make DamageArea active, triggering Damage to be taken.
            Animator.Play("Attack", -1, 0);
            NextAttackTime = Time.time + AttackRate;
        }
    }

    protected override void PlayAnimationDeath()
    {
        Animator.Play("Die");
        Destroy(gameObject, 1f);
        return;
        // Add to score or collateral damage score
    }

    void OnCollisionEnter2D(Collision2D collision)
    {
        if (collision.gameObject.tag == "Ground") { Grounded = true; } else { }
        // not detecting when the sprite has left the ground... else function doesn't work
    }

    void OnTriggerEnter2D(Collider2D collision)
    {
        try
        {
            if (collision.gameObject.tag == "Friend")
            {   // When E_Swordsman plays Attack.anim, will make the DamageArea active briefly to damage Target.
                // Will damage Target's Health with E_Swordsman's AttackDamage.
                collision.GetComponent<HealthSystem>().DamageTaken(AttackDamage);

                // If there's one f_swordsman vs one e_gunman, health system works.
                // If there's one f_swordsman vs one e_swordsman, get null reference exception after one hit?
            }
        }
        catch (System.NullReferenceException) { };
    }
}