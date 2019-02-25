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
        gameObject.layer = 12; // Start "boundary" layer.
    }

    protected override void Update()
    {   // Changing back to "Friend" layer.
        if (transform.position.x >= -8) { gameObject.layer = 9; }
        base.Update();
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

    protected override void OnDrawGizmos()
    {
        Gizmos.color = Color.red;
        Gizmos.DrawWireSphere(transform.position, AttackRadius);
        Gizmos.DrawWireSphere(transform.position, LookRadius);
    }
}