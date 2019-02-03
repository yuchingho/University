using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class E_Gunman : AI_Enemy {

    // Child Class E_Gunman inheriting from AI_Enemy.
    [Space( 10), Header("[^ Child:   E_Gunman ] Weapon")]
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

    protected override void Update()
    {
        if (OnCastle == true)
        {
            MovementSpeed = 0;
            base.Update();
        }
        else
        {
            base.Update();
        }
    }
    
    protected override void Shoot()
    {   // Instantiating Bullet prefab.
        GameObject Bullet = Instantiate(Projectile, FireLocation.position, FireLocation.rotation);
        Bullet bullet = Bullet.GetComponent<Bullet>();
        if (bullet != null) { bullet.Seek(Target); } // Using Bullet's script Seek method.
        // Add Bullet's Damage on the prefab. AttackDamage value here is just reference.
    }

    void OnCollisionEnter2D(Collision2D collision)
    {
        if (collision.gameObject.tag == "Ground")
        {
            OnCastle = false;
            Grounded = true;
            LookRadius = 4f;
            AttackRadius = 4f;
        }

        if (collision.gameObject.tag == "Castle")
        {
            OnCastle = true;
            Grounded = true;
            LookRadius = 10f;
            AttackRadius = 10f;
        }
    }
}