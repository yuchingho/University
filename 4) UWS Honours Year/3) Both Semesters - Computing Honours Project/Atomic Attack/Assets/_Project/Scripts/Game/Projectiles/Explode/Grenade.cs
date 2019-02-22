using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Grenade : _Explode {

    protected Transform Target;
    Rigidbody2D Rigidbody2D;

    float Rand;
    [SerializeField] protected int Speed = 3;
    [SerializeField] protected int ThrowAngle = 150;

    public void Throw(Transform target)
    {
        Target = target;
    }

    void Reset()
    {
        Damage = 5;
        ExplosionRadius = 10;
    }

    protected override void Start()
    {
        Rand = Random.Range(1, 100);
        int ThrowDir = Target.GetComponent<AI_Human>().MovementDirection;
        Rigidbody2D = GetComponent<Rigidbody2D>();
        Rigidbody2D.AddForce(transform.up * ThrowAngle);
        Rigidbody2D.AddForce(transform.right * ThrowAngle * ThrowDir);
        // Adding ThrowDir so Grenade will throw in the correct direction when facing enemy of 1 or -1.
    }

    // Whenever Grenade is thrown, will have a random spin along the Z axis.
    protected override void Update() { transform.Rotate(Vector3.forward * Rand); }

    // if magnifed = true, add more damage
    void OnCollisionEnter2D(Collision2D collision)
    {
        //add damage in explosion radius
        //Target.GetComponent<HealthSystem>().DamageTaken(Damage);
        Instantiate(Explosion, new Vector3(transform.position.x, transform.position.y, transform.position.z-1), transform.rotation);
        Destroy(gameObject);
    }
}