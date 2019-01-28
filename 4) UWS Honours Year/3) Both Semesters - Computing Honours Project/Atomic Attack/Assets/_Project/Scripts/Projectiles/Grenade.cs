using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Grenade : MonoBehaviour {

    [SerializeField] GameObject Explosion;
    public float Speed;
    public float Damage;
    public float ExplosionRadius;
    [SerializeField] float ThrowAngle;
    float Rand;

    HealthSystem HealthSystem;
    Rigidbody2D Rigidbody2D;

    private void Start()
    {
        Rand = Random.Range(1, 100);
        Rigidbody2D = GetComponent<Rigidbody2D>();
        Rigidbody2D.AddForce(transform.up * ThrowAngle);
        Rigidbody2D.AddForce(transform.right * ThrowAngle);
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