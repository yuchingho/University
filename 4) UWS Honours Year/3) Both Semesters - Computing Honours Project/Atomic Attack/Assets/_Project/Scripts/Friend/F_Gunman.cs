using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class F_Gunman : AI_Friend {
    [Space( 10), Header("[^ Child: AI_Friend ]")]
    // Child Class F_Gunman inheriting from AI_Friend.
    [Space(-10), Header("[^ Child:   F_Gunman ]")]
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

    protected override void Shoot()
    {   // Instantiating Bullet prefab.
        GameObject Bullet = Instantiate(Projectile, FireLocation.position, FireLocation.rotation);
        _Bullet bullet = Bullet.GetComponent<_Bullet>();
        // Using Bullet's script Seek method.
        if (bullet != null) { bullet.Seek(Target); } 
    }

    void OnCollisionEnter2D(Collision2D collision)
    {
        if (collision.gameObject.tag == "Ground") { Grounded = true; } else { }
        // not detecting when the sprite has left the ground... else function doesn't work
    }
}