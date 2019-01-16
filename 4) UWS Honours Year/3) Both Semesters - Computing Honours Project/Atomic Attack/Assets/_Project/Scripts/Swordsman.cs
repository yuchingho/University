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
        MovementSpeed = 1.5f;
        LookRadius = 4f;   // Swordsman = 4f. Gunman = 6f.
        AttackRadius = 1f; // Swordsman = 1f. Gunman = 6f
    }

    /*
    protected override void UpdateTargetEnemy()
    {   // Overriding Friend's Class Health method
        base.UpdateTargetEnemy();
    }
    */
}