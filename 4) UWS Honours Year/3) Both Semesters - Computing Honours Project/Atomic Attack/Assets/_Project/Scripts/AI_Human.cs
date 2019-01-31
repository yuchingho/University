using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public abstract class AI_Human : MonoBehaviour {

    // Parent Class for Inheritance.
    Rigidbody2D Rigidbody2D;
    protected HealthSystem HealthSystem;

    [Space(-10), Header("[ Parent: AI_Human ] Movement")]
    public int CostValue;
    public int ScoreValue;

    [Space( 10), Header("[ Parent: AI_Human ] Movement")]
    [SerializeField] protected float MovementSpeed;
    protected float MovementDirection;
    protected float MovementSpeedInitial;
    [SerializeField] protected float VelocityCurrent;
    [SerializeField] protected bool Grounded;
    [SerializeField] protected bool RunAway;

    [Space( 10), Header("[ Parent: AI_Human ] Target")]
    public Transform Target;
    [SerializeField] protected float TargetHealth;
    public Transform FinalTarget;

    [Space( 10), Header("----------------- Stats -----------------")]
    [SerializeField] protected float LookRadius;
    [SerializeField] protected float AttackRadius;

    protected virtual void Start()
    {
        HealthSystem = GetComponent<HealthSystem>();
        Rigidbody2D = GetComponent<Rigidbody2D>();
        MovementSpeedInitial = MovementSpeed;
    }

    // Child Classes Enemy.cs and Friend.cs have InvokeRepeating UpdateTarget() every 0.25f.
    // Is basically another "Update" Method, which if has a Target, will go to LookatTarget().
    protected virtual void Update() { Movement(); }

    protected virtual void LookAtTarget()
    {   // Sprites flipping to look at its Target.
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
        VelocityCurrent = Rigidbody2D.velocity.magnitude;
    }

    // Animator.Play(state, layer, normalizedTime);
    // Need the other 2 overloads, otherwise won't repeat every AttackRate.
    protected abstract void PlayAnimationAttack();
    protected abstract void PlayAnimationDeath();

    // Same as "virtual void", but has to be called in Child classes.
    // Since added "abstract void" here, have to add "abstract" at start of class.
    protected abstract void OnDrawGizmos();

    // Added two new Layers - "Enemy" and "Friend".
    // Enemies and Friends can overlap.
    // The two layers colliding have been disabled.
    // Edit > Project Settings > Physics 2D.
}