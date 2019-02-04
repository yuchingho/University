using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class AI_Friend : AI_Human {

    // Child Class AI_Friend inheriting from AI_Human.
    [Space( 10), Header("[^ Child: AI_Friend ]")]
    public int FriendCounter = 1;








    protected override void Start()
    {   // If GameObjectTag == Friend, will target Enemy.
        base.Start();
        FinalTarget = GameObject.Find("Castle Health").transform;
        InvokeRepeating("UpdateTargetEnemy", 0f, 0.25f);
    }

    // Child Classes Enemy.cs and Friend.cs have InvokeRepeating UpdateTarget() every 0.25f.
    // Is basically another "Update" Method, which if has a Target, will go to LookatTarget().
    protected virtual void Update()
    {
        try
        {
            if (HealthSystem.Deceased == true && Grounded == true)
            {
                PlayAnimationDeath();
            }
            else
            {
                Movement();
                StatusSuffocate();
                StatusPoisoned();
                StatusBurned();
            }
        }
        catch (System.NullReferenceException) { };
    }

    protected virtual void UpdateTargetEnemy()
    {   // If GameObjectTag == Friend, will target Enemy.
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
        if (NearestEnemy != null && ShortestDistance <= LookRadius)
        {   // Updating Target to Nearest Enemy, and getting Target's Health.
            Target = NearestEnemy.transform;
            TargetHealth = NearestEnemy.GetComponent<HealthSystem>().Health;
            LookAtTarget();
        }
        else
        {   // If no more Enemies, look towards Castle.
            Target = FinalTarget;
            LookAtTarget();
        }
    }

    protected virtual void LookAtTarget()
    {   // Sprites flipping to look at its Target.
        try
        {
            Vector3 dir = Target.position - transform.position;
            float Angle = Mathf.Atan2(dir.y, dir.x) * Mathf.Rad2Deg;
            if (Angle <= 160) { MovementDirection = -1; }
            if (Angle >= 170) { MovementDirection = 1; Angle -= 180; }
            transform.rotation = Quaternion.AngleAxis(Angle, Vector3.forward);
        }
        catch (System.NullReferenceException) { };
    }

    protected override void OnDrawGizmos()
    {
        Gizmos.color = Color.red;
        Gizmos.DrawWireSphere(transform.position, AttackRadius);
        Gizmos.DrawWireSphere(transform.position, LookRadius);
    }
}