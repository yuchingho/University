using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Swordsman : AI_Human {

    [Space(-10), Header("[^ Child: Swordsman ] Attack")]
    [SerializeField] float AttackDamage;
    [SerializeField] float AttackSpeed;
    [SerializeField] float SwordCooldown;
    [SerializeField] GameObject TargetImpact;

    void Reset()
    {
        MovementSpeed = 1.2f;
        LookRadius = 3f;
        AttackRadius = 1.4f;
    }

    /*
    protected override void UpdateTargetEnemy()
    {   // Overriding Friend's Class Health method
        base.UpdateTargetEnemy();
    }
    */
}