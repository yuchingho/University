using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Missile_Fluorine : Missile {
    // Element 09.




    void Reset()
    {
        Magnified = true;
        Damage = 250;
        ExplosionRadius = 5.00f;
        ExplosionScale = 0.7f;
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