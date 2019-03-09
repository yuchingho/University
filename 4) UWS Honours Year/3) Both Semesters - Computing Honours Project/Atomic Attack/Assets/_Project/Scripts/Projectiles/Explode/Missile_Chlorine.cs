using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Missile_Chlorine : Missile {
    // Element 17.

    
    

    void Reset()
    {
        Magnified = true;
        Damage = 150;
        ExplosionRadius = 4.00f;
        ExplosionScale = 0.25f;
        ExplosionForce = 15;
    }

    void OnCollisionEnter2D(Collision2D collision)
    {
        ExplosionDamage();
        Instantiate(Explosion, new Vector3(transform.position.x, transform.position.y, transform.position.z - 1), transform.rotation);
        Instantiate(Mist, new Vector2(0, 1.53f), Quaternion.identity);
        Destroy(gameObject);
    }
}