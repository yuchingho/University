using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Friend : AI_Human {

	protected override void Start()
    {   // If GameObjectTag == Friend, will target Enemy.
        base.Start();
        SpawnPoint = GameObject.Find("SpawnPoint Friend").transform;
        EndTarget = GameObject.Find("SpawnPoint Enemy").transform;
        InvokeRepeating("UpdateTargetEnemy", 0f, 0.25f);
    }

    // Prioritise Barrels...
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
        try
        {
            if (NearestEnemy != null && ShortestDistance <= LookRadius)
            {   // Updating Target to Nearest Enemy, and getting Target's Health.
                Target = NearestEnemy.transform;
                TargetHealth = NearestEnemy.GetComponent<HealthSystem>().Health;
                LookAtTarget();
            }
            else
            {   // If no more Enemies, look towards Castle.
                Target = EndTarget;
                LookAtTarget();
            }
        }
        catch (System.NullReferenceException) { };
    }

    protected override void OnDrawGizmos()
    {
        Gizmos.color = Color.clear;
        Gizmos.DrawWireSphere(transform.position, AttackRadius);
        Gizmos.DrawWireSphere(transform.position, LookRadius);
    }
}