using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class F01_Hydrogen : F_Gunman {

    [Space( 10), Header("[^ Child: F01_Hydrogen ]")]
    #pragma warning disable
    [SerializeField] string Effect = "AoE Damage";
    Magnifyed Magnify;  // -------------------------------------------------------------------------
    void Reset()
    {
        MovementSpeed = 1f;
        AttackRate = 2f;
        LookRadius = 3f;
        AttackRadius = LookRadius;
    }

    protected override void LookAtTarget()
    {   // Sprites flipping to look at its Target.
        if (OnTheCastle == true)
        {   // When on Castle, different Angle algorithm so will face Target correctly.
            Vector3 dir = Target.position - transform.position;
            float Angle = Mathf.Atan2(dir.z, dir.x) * Mathf.Rad2Deg;
            if (Angle <= 160) { MovementDirection = -1; }
            if (Angle >= 170) { MovementDirection = 1; Angle -= 180; }
            transform.rotation = Quaternion.AngleAxis(Angle, Vector3.left);
        }
        else
        {
            if (Target != null)
            {
                Vector3 dir = Target.position - transform.position;
                float Angle = Mathf.Atan2(dir.y, dir.x) * Mathf.Rad2Deg;
                if (Angle <= 160) { MovementDirection = -1; }
                if (Angle >= 170) { MovementDirection = 1; Angle -= 180; }
                if (Angle <= -170) { MovementDirection = 1; Angle += 180; }
                transform.rotation = Quaternion.AngleAxis(Angle, Vector3.forward);
            }
        }
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
    {   // Instantiating Grenade prefab with delay so inline with Throw Animation.
        yield return new WaitForSeconds(0.35f);
        Instantiate(Projectile,
        new Vector3(FireLocation.position.x, FireLocation.position.y, FireLocation.position.z - 1), FireLocation.rotation);
        Grenade grenade = GetComponent<Grenade>();
        if (grenade != null) { grenade.Seek(Target); }
    }
}