using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class F_Gunman : AI_Friend {

    // Child Class F_Gunman inheriting from AI_Friend.
    [Space(-10), Header("[^ Child:   F_Gunman ]")]
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

    protected override void Shoot()
    {   // Instantiating Bullet prefab.
        GameObject Bullet = Instantiate(Projectile,
        new Vector3 (FireLocation.position.x, FireLocation.position.y, FireLocation.position.z-1), FireLocation.rotation);
        _Bullet bullet = Bullet.GetComponent<_Bullet>();
        // Using Bullet's script Seek method.
        if (bullet != null) { bullet.Seek(Target); } 
    }

    void OnCollisionEnter2D(Collision2D collision)
    {
        if (collision.gameObject.tag == "Ground")
        {
            Grounded = true;
            if (transform.position.x >= -9 && GrabbedByMouse == true)
            {
                gameObject.layer = 9;
                StartCoroutine(HitTheGround());
            }
        }
    }
}