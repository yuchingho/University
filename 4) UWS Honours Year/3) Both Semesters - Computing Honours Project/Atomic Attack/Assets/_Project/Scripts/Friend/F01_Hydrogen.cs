using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class F01_Hydrogen : F_Gunman {

    // Child Class F01_Hydrogen inheriting from F_Gunman.
    //[Space( 10), Header("[^ Child: F01_Hydrogen ] Damage")]
    //string Notes2 = "MovementSpeed = 1f \nHealth = 6f \nAttackDamage = 3f";
    //[Space(-10), Header("1.2f = MovementSpeed")]
    //[Space(-10), Header("6.0f = Health")]

    void Reset()
    {
        MovementSpeed = 1f;
        LookRadius = 3f;
        AttackRadius = LookRadius;
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