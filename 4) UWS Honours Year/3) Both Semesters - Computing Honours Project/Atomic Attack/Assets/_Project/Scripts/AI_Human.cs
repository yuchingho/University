using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public abstract class AI_Human : MonoBehaviour {
    // Parent Class for Inheritance.
    [Space(-10), Header("[ Parent: AI_Human ] Cost")]
    [SerializeField] protected int CostValue;
    [SerializeField] protected int ScoreValue;
    protected int CounterValue = 1;

    [Space(10), Header("[ Parent: AI_Human ] Movement")]
    [SerializeField] protected bool RunAway;
    protected Transform SpawnPoint;
    protected Transform EndTarget;
    protected float MovementDirection;
    protected float MovementSpeedInitial;
    [SerializeField] protected float MovementSpeed;
    [SerializeField, Range(1, 3)] protected float RunAwayTimer;
    [SerializeField] protected float VelocityCurrent;
    [SerializeField] protected bool Grounded;

    [Space(10), Header("[ Parent: AI_Human ] Damage")]
    [SerializeField] protected int AttackDamage;
    [SerializeField] protected float LookRadius;
    [SerializeField] protected float AttackRadius;
    [SerializeField] protected float AttackRate;
    protected float NextAttackTime;
    public Transform Target;
    public float TargetHealth;
    [SerializeField] protected GameObject TargetImpactEffect;

    Rigidbody2D Rigidbody2D;
    Animator Animator;

    protected virtual void Start()
    {
        Rigidbody2D = GetComponent<Rigidbody2D>();
        Animator = GetComponent<Animator>();
        MovementSpeedInitial = MovementSpeed;
        // Child Classes Enemy.cs and Friend.cs have InvokeRepeating UpdateTarget...() every 0.25f.
        // If UpdateTarget...() has a Target, will go to LookatEnemy().
        NextAttackTime = 0;
    }

    protected virtual void Update()
    {
        VelocityCurrent = Rigidbody2D.velocity.magnitude;
        if (Grounded == true) { Movement(); }
    }

    protected virtual void LookAtTarget()
    {
        // Sprites flipping to look at its Target.
        Vector3 dir = Target.position - transform.position;
        float Angle = Mathf.Atan2(dir.y, dir.x) * Mathf.Rad2Deg;
        if (Angle <= 160) { MovementDirection = -1; }
        if (Angle >= 170) { MovementDirection =  1; Angle -= 180; }
        transform.rotation = Quaternion.AngleAxis(Angle, Vector3.forward);
    }
    
    protected virtual void Movement()
    {   // When velocity = 0, can't start moving again. fix???
        Rigidbody2D.velocity = new Vector2(MovementSpeed * -MovementDirection, 0);
        transform.localScale = new Vector2(0.3f * MovementDirection, 0.3f);

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

    protected virtual void PlayAnimationAttack()
    {
        if (Time.time > NextAttackTime)
        {
            // Animator.Play(state, layer, normalizedTime);
            // Need the other 2 overloads, otherwise won't repeat every AttackRate.
            // Playing Animation will make DamageArea active, triggering Damage to be taken.
            Animator.Play("Attack", -1, 0f);
            NextAttackTime = Time.time + AttackRate;
        }
    }

    protected virtual void PlayAnimationDeath()
    {
        Animator.Play("Die");
    }


    // Method to add to score
    // Add collateral damage

    void OnCollisionEnter2D(Collision2D collision)
    {
        if (collision.gameObject.tag == "Ground") { Grounded = true; }
        // not detecting when the sprite has left the ground... else function doesn't work
    }

    // Same as "virtual void", but has to be called in Child classes.
    // Since added "abstract void" here, have to add "abstract" at start of class.
    protected abstract void OnDrawGizmos();
}