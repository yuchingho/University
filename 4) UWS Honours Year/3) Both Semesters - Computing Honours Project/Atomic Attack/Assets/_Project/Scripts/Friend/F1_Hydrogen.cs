using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class F1_Hydrogen : F_Gunman {
    // Child Class F1_Hydrogen inheriting from F_Gunman.

    
    





    void Reset()
    {   // Health = 6;
        CostValue = 0;
        ScoreValue = 0;
        MovementSpeed = 1f;
        RunAwayTimer = 3f;
        AttackDamage = 3;
        LookRadius = 3f;
        AttackRadius = 3f;
        AttackRate = 2f;
    }

    protected override void PlayAnimationAttack()
    {
        if (Time.time > NextAttackTime)
        {
            base.PlayAnimationAttack();
            StartCoroutine(ThrowGrenade());
        }
    }

    protected override void Shoot() { }
    IEnumerator ThrowGrenade()
    {   // Instantiating Grenade prefab.
        yield return new WaitForSeconds(0.35f);
        Instantiate(Projectile, FireLocation.position, FireLocation.rotation);
        // Add Grenade's Damage on the prefab. AttackDamage value here is just reference.
    }
}