using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class AI_Enemy : AI_Human {

    // Child Class AI_Enemy inheriting from Parent AI_Human.
    protected override void Start()
    {   // If GameObjectTag == Enemy, will target Friend.
        base.Start();
        FinalTarget = GameObject.Find("Enemy End").transform;
        InvokeRepeating("UpdateTargetFriend", 0f, 0.25f);
    }

    protected virtual void UpdateTargetFriend()
    {   // If GameObjectTag == Enemy, will target Friend.
        GameObject[] Friends = GameObject.FindGameObjectsWithTag("Friend");
        float ShortestDistanceF = Mathf.Infinity;
        GameObject NearestFriend = null;
        foreach (GameObject NewFriend in Friends)
        {   // ForEach algorithm to calculate Nearest Enemy.
            float DistanceToFriend = Vector2.Distance(transform.position, NewFriend.transform.position);
            if (DistanceToFriend < ShortestDistanceF)
            {
                ShortestDistanceF = DistanceToFriend;
                NearestFriend = NewFriend;
            }
        }
        if (NearestFriend != null && ShortestDistanceF <= LookRadius)
        {   // Updating Target to Nearest Enemy, and getting Target's Health.
            Target = NearestFriend.transform;
            TargetHealth = NearestFriend.GetComponent<HealthSystem>().Health;
            LookAtTarget();
        }
        else
        {   // If no more Enemies, look towards Friend Start Point.
            Target = FinalTarget;
            LookAtTarget();
        }
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