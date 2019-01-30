using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class AI_Enemy : AI_Human {

    // Child Class AI_Enemy inheriting from Parent AI_Human.
    protected override void Start()
    {   // If GameObjectTag == Enemy, will target Friend.
        base.Start();
        //SpawnPoint = GameObject.Find("SpawnPoint Enemy").transform;
        FinalTarget = GameObject.Find("SpawnPoint Friend").transform;
        InvokeRepeating("UpdateTargetFriend", 0f, 0.25f);
    }

    protected virtual void UpdateTargetFriend()
    {   // If GameObjectTag == Enemy, will target Friend.
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
        {
            if (NearestEnemy != null && ShortestDistance <= LookRadius)
            {   // Updating Target to Nearest Enemy, and getting Target's Health.
                Target = NearestEnemy.transform;
                TargetHealth = NearestEnemy.GetComponent<HealthSystem>().Health;
                LookAtTarget();
            }
            else
            {   // If no more Enemies, look towards Friend Start Point.
                Target = FinalTarget;
                LookAtTarget();
            }
        }
        catch (System.NullReferenceException) { };
    }

    protected override void PlayAnimationAttack() { throw new System.NotImplementedException(); }
    protected override void PlayAnimationDeath () { throw new System.NotImplementedException(); }
    protected override void OnDrawGizmos()
    {
        Gizmos.color = Color.blue;
        Gizmos.DrawWireSphere(transform.position, AttackRadius);
        Gizmos.DrawWireSphere(transform.position, LookRadius);
    }
}