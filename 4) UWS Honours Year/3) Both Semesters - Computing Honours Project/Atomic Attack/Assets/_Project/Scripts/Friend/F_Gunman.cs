using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class F_Gunman : AI_Friend {
    [Space( 10), Header("[^ Child: AI_Friend ]")]
    // Child Class F_Gunman inheriting from AI_Friend.
    [Space(-10), Header("[^ Child:   F_Gunman ] Weapon")]
    #pragma warning disable
    [SerializeField] string AttackDamage = "2 [RO] Add to Prefab";
    [SerializeField] protected GameObject Projectile;
    [SerializeField] protected Transform FireLocation;



    void Reset()
    {
        MovementSpeed = 1f;
        AttackRate = 0.75f;
        LookRadius = 4f;
        AttackRadius = LookRadius;
    }

    protected virtual void Shoot()
    {   // Instantiating Bullet prefab.
        GameObject Bullet = Instantiate(Projectile, FireLocation.position, FireLocation.rotation);
        Bullet bullet = Bullet.GetComponent<Bullet>();
        if (bullet != null) { bullet.Seek(Target); } // Using Bullet's script Seek method.
        // Add Bullet's Damage on the prefab. AttackDamage value here is just reference.
    }

    void OnCollisionEnter2D(Collision2D collision)
    {
        if (collision.gameObject.tag == "Ground") { Grounded = true; } else { }
        // not detecting when the sprite has left the ground... else function doesn't work
    }
}