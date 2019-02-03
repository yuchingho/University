using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Grenade : _Explode {

    HealthSystem HealthSystem;
    Rigidbody2D Rigidbody2D;

    [SerializeField] float Speed;
    [SerializeField] GameObject Explosion;
    [SerializeField] float ExplosionRadius;
    [SerializeField] float ThrowAngle;
    float Rand;

    void Start()
    {
        Rand = Random.Range(1, 100);
        Rigidbody2D = GetComponent<Rigidbody2D>();
        Rigidbody2D.AddForce(transform.up * ThrowAngle);
        Rigidbody2D.AddForce(transform.right * ThrowAngle);
    }

    // Whenever Grenade is thrown, will spin along the Z axis.
    void Update() { transform.Rotate(Vector3.forward * Rand); }
    // if magnifed = true, add more damage

    void OnCollisionEnter2D(Collision2D collision)
    {
        //add damage in explosion radius
        // don't need to calculate FallDamage cause damage should already kill them
        //Target.GetComponent<HealthSystem>().DamageTaken(Damage);
        Instantiate(Explosion, transform.position, transform.rotation);
        Destroy(gameObject);
    }
}