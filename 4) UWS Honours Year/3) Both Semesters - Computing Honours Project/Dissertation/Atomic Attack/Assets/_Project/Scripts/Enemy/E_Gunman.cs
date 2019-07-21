using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class E_Gunman : AI_Enemy {

    // Child Class E_Gunman inheriting from AI_Enemy.
    [Space( 10), Header("[^ Child:   E_Gunman ]")]
    #pragma warning disable
    [SerializeField] string AttackDamage = "[Read Only Prefab]";
    [SerializeField] protected GameObject Projectile;
    [SerializeField] protected Transform FireLocation;

    void Reset()
    {
        MovementSpeed = 1.00f;
           AttackRate = 0.75f;
           LookRadius = 3.00f;
         AttackRadius = 3.00f;
    }

    protected override void Movement()
    {   // Activating Blinded.
        if (Blinded == true)  { AttackRate = 2.00f; }
        if (Blinded == false) { AttackRate = 0.75f; }
        base.Movement();
    }

    protected override void Update()
    {
        if (OnTheCastle == true)
        {
            MovementSpeed = 0;
            base.Update();
        }
        else { base.Update(); }
    }
    
    protected override void Shoot()
    {   // Instantiating Bullet prefab.
        GameObject Bullet = Instantiate(Projectile,
        new Vector3(FireLocation.position.x, FireLocation.position.y, FireLocation.position.z - 1), FireLocation.rotation);
        _Bullet bullet = Bullet.GetComponent<_Bullet>();
        // Using Bullet's script Seek method.
        if (bullet != null) { bullet.Seek(Target); }
    }

    void OnCollisionEnter2D(Collision2D collision)
    {
        if (collision.gameObject.tag == "Ground")
        {
                Grounded = true;
             OnTheCastle = false;
              LookRadius = 3.00f;
            AttackRadius = 3.00f;
            if (GrabbedByMouse == true)
            { StartCoroutine(HitTheGround()); }
        }

        if (collision.gameObject.tag == "Castle")
        {
                Grounded = true;
             OnTheCastle = true;
              LookRadius = 15.00f;
            AttackRadius = 15.00f;
            GrabbedByMouse = false;
        }
    }
}