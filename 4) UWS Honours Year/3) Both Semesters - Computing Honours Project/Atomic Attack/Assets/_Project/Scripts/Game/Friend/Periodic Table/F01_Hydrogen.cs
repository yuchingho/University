using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class F01_Hydrogen : F_Gunman {

    [Space( 10), Header("[^ Child: F01_Hydrogen ]")]
    #pragma warning disable
    [SerializeField] string Effect = "AoE Damage.";
    Magnifyed Magnify;  // -------------------------------------------------------------------------





    void Reset()
    {
        MovementSpeed = 1.00f;
           AttackRate = 2.00f;
           LookRadius = 3.00f;
         AttackRadius = 3.00f;
    }

    protected override void PlayAnimationAttack()
    {
        if (Time.time > NextAttackTime)
        {
            base.PlayAnimationAttack();
            StartCoroutine(ThrowGrenade());
        }
    }

    protected override void Shoot() { /* So won't spawn double Grenades. */ }
    IEnumerator ThrowGrenade()
    {   // Instantiating Grenade prefab with delay so inline with Throw Animation.
        yield return new WaitForSeconds(0.35f);
        GameObject Grenade = Instantiate(Projectile,
        new Vector3(FireLocation.position.x, FireLocation.position.y, FireLocation.position.z - 1), FireLocation.rotation);
        Grenade grenade = Grenade.GetComponent<Grenade>();
        if (grenade != null) { grenade.Throw(Target); }
    }
}