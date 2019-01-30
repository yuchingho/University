using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Grenade : MonoBehaviour {

    HealthSystem HealthSystem;
    Rigidbody2D Rigidbody2D;

    public int Damage;
    public float Speed;
    [SerializeField] GameObject Explosion;
    public float ExplosionRadius;
    [SerializeField] float ThrowAngle;
    public bool Magnify; // if grenade in magnified area, will cause lil guys to fly away.

    float Rand;

    private void Start()
    {
        Rand = Random.Range(1, 100);
        Rigidbody2D = GetComponent<Rigidbody2D>();
        Rigidbody2D.AddForce(transform.up * ThrowAngle);
        Rigidbody2D.AddForce(transform.right * ThrowAngle);
        Magnify = false;
    }

    // Whenever Grenade is thrown, will spin along the Z axis.
    void Update() { transform.Rotate(Vector3.forward * Rand); }

    void OnCollisionEnter2D(Collision2D collision)
    {
        //add damage in explosion radius
        //Target.GetComponent<HealthSystem>().DamageTaken(Damage);
        Instantiate(Explosion, transform.position, transform.rotation);
        Destroy(gameObject);
    }
}