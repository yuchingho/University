using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Grenade : _Explode {

    F01_Hydrogen F01_Hydrogen;
    Rigidbody2D Rigidbody2D;
    protected HealthSystem HealthSystem;
    [SerializeField] protected Transform Target;
    [SerializeField] float Speed;
    [SerializeField] GameObject Explosion;
    [SerializeField] float ExplosionRadius;
    [SerializeField] float ThrowAngle;
    float Rand;
    int ThrowDirection;

    public void Seek(Transform target)
    {
        Target = target;
    }

    void Start()
    {
        //F01_Hydrogen = GetComponent<F01_Hydrogen>();
        //ThrowDirection = F01_Hydrogen.MovementDirection;
        //Debug.Log(ThrowDirection);
        Debug.Log(Target);
        // Get Target's movement direction to * it by, and make sure throwing in right direction

        // smh wrong with f01_hydrogen throw grenade coroutine and shoot, not targetting correctly

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
        //Target.GetComponent<HealthSystem>().DamageTaken(Damage);
        Instantiate(Explosion, new Vector3(transform.position.x, transform.position.y, transform.position.z-1), transform.rotation);
        Destroy(gameObject);
    }
}