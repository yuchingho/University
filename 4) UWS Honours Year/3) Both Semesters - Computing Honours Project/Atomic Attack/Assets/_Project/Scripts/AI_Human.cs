using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class AI_Human : MonoBehaviour {

    [Space(-10), Header("[ Parent: Human ] Movement")]
    Transform SpawnPoint;
    [SerializeField] Transform Target;
    Transform EndTarget;
    [SerializeField] protected float MovementSpeed;
    float MovementSpeedInitial;
    [SerializeField] protected float Direction;
    [SerializeField] protected bool RunAway;
    [SerializeField, Range(1, 3)] float RunAwayTimer = 3;
    [SerializeField] protected float VelocityCurrent;
    [SerializeField] protected bool Grounded;

    [Space(10), Header("[ Parent: Human ] Health")]
    [SerializeField] protected float HealthMax;
    [SerializeField] protected float HealthCurrent;

    [Space(10), Header("[ Parent: Human ] Cost")]
    [SerializeField] protected float CostValue;
    [SerializeField] protected float CounterValue = 1;
    [SerializeField] protected float ScoreValue;

    [Space(20)]
    [SerializeField] protected float LookRadius;
    [SerializeField] protected float AttackRadius;

    Rigidbody2D Rigidbody2D;
    Animator Animator;

    void Start()
    {
        Rigidbody2D = GetComponent<Rigidbody2D>();
        Animator = GetComponent<Animator>();
        MovementSpeedInitial = MovementSpeed;

        if (this.gameObject.CompareTag("Enemy"))
        {   // If GameObjectTag == Enemy, will target Friend.
            SpawnPoint = GameObject.Find("SpawnPoint Enemy").transform;
            EndTarget = GameObject.Find("SpawnPoint Friend").transform;
            InvokeRepeating("UpdateTargetFriend", 0f, 0.25f);
        }
        if (this.gameObject.CompareTag("Friend"))
        {   // If GameObjectTag == Friend, will target Enemy.
            SpawnPoint = GameObject.Find("SpawnPoint Friend").transform;
            EndTarget = GameObject.Find("SpawnPoint Enemy").transform;
            InvokeRepeating("UpdateTargetEnemy", 0f, 0.25f);
        }
    }

    void Update()
    {
        VelocityCurrent = Rigidbody2D.velocity.magnitude;
        if (Grounded == true) { Movement(); }
    }

    // Prioritise Barrels...
    protected virtual void UpdateTargetEnemy()
    {   // If GameObjectTag == Enemy, will target Friend.
        GameObject[] Enemies = GameObject.FindGameObjectsWithTag("Enemy");
        float ShortestDistance = Mathf.Infinity;
        GameObject NearestEnemy = null;
        foreach (GameObject NewEnemy in Enemies)
        {   // ForEach algorithm to calculate Nearest Enemy.
            float DistanceToEnemy = Vector2.Distance(transform.position, NewEnemy.transform.position);
            if (DistanceToEnemy < ShortestDistance)
            {
                ShortestDistance = DistanceToEnemy;
                NearestEnemy = NewEnemy;
            }
        }
        try
        {   // Updating Target to Nearest Enemy.
            if (NearestEnemy != null && ShortestDistance <= LookRadius)
            {
                Target = NearestEnemy.transform;
                LookAtTarget();
                WithinAttackRange();
            }
            else
            {
                Target = EndTarget;
                LookAtTarget();
                WithinAttackRange();
            }
        }
        catch (System.NullReferenceException) { };
    }

    // Prioritise Barrels...
    protected virtual void UpdateTargetFriend()
    {   // If GameObjectTag == Friend, will target Enemy.
        GameObject[] Enemies = GameObject.FindGameObjectsWithTag("Friend");
        float ShortestDistance = Mathf.Infinity;
        GameObject NearestEnemy = null;
        foreach (GameObject NewEnemy in Enemies)
        {   // ForEach algorithm to calculate Nearest Enemy.
            float DistanceToEnemy = Vector2.Distance(transform.position, NewEnemy.transform.position);
            if (DistanceToEnemy < ShortestDistance)
            {
                ShortestDistance = DistanceToEnemy;
                NearestEnemy = NewEnemy;
            }
        }
        try
        {   // Updating Target to Nearest Enemy.
            if (NearestEnemy != null && ShortestDistance <= LookRadius)
            {
                Target = NearestEnemy.transform;
                LookAtTarget();
                WithinAttackRange();
            }
            else {
                Target = EndTarget;
                LookAtTarget();
                WithinAttackRange();
            }
        }
        catch (System.NullReferenceException) { };
    }

    protected virtual void LookAtTarget()
    {
        // Getting AI_Human Script of Target, and checking if its "Grounded" is true.
        // ------ Not working, fix.
        //AI_Human AI_Human = Target.GetComponent<AI_Human>();
        //if (AI_Human.Grounded == true)
        //{   // Sprites flipping to look at its Target.
        Vector3 dir = Target.position - transform.position;
        float angle = Mathf.Atan2(dir.y, dir.x) * Mathf.Rad2Deg;
        if (angle <= 160) { Direction = -1; }
        if (angle >= 170) { Direction =  1; angle -= 180; }
        transform.rotation = Quaternion.AngleAxis(angle, Vector3.forward);
        //}
    }

    // When velocity = 0, can't start moving again. fix...

    protected virtual void Movement()
    {
        Rigidbody2D.velocity = new Vector2(MovementSpeed * -Direction, 0);
        transform.localScale = new Vector2(0.3f * Direction, 0.3f);
    }

    protected virtual void WithinAttackRange()
    {
        float AttackRange = Vector2.Distance(transform.position, Target.transform.position);
        if (AttackRange <= AttackRadius)
        {
            MovementSpeed = 0;
            Animator.Play("Attack");
        }
        else
        {
            MovementSpeed = MovementSpeedInitial;
            Animator.Play("Run");
        }

    }

    protected virtual void Death()
    {

    }

    // Method to add to score
    // Add collateral damage

    void OnCollisionEnter2D(Collision2D collision)
    {
        if (collision.gameObject.tag == "Ground") { Grounded = true; }
        else { Grounded = false; }
    }

    void OnDrawGizmos()
    {
        if (this.gameObject.CompareTag("Enemy"))
        {
            Gizmos.color = Color.blue;
            Gizmos.DrawWireSphere(transform.position, AttackRadius);
            Gizmos.DrawWireSphere(transform.position, LookRadius);
        }

        if (this.gameObject.CompareTag("Friend"))
        {
            Gizmos.color = Color.red;
            Gizmos.DrawWireSphere(transform.position, AttackRadius);
            Gizmos.DrawWireSphere(transform.position, LookRadius);
        }
    }
}