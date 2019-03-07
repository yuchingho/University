using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Grenade : _Explode {

    Transform Target;
    Rigidbody2D Rigidbody2D;

    float Rand;
    int ThrowDir;
    int ThrowAngle = 90;

    public void Throw (Transform target) { Target = target; }

    void Reset()
    {
        Damage = 5;
        ExplosionRadius = 0.25f;
        ExplosionScale = 0.1f;
    }
    
    protected virtual void Start()
    {
        Rand = Random.Range(1, 100);
        Rigidbody2D = GetComponent<Rigidbody2D>();
        if (Target != null)
        {
            if (Target.gameObject.name == "E_Gunman(Clone)" || Target.gameObject.name == "E_Swordsman(Clone)")
            {      ThrowDir = Target.GetComponent<AI_Human>().MovementDirection; }
            else { ThrowDir = 1; }  // ThrowDir will be -1 (left) or 1 (right).
        }   // Adding ThrowDir so Grenade will throw in the correct direction.
        Rigidbody2D.AddForce(transform.up    * ThrowAngle);
        Rigidbody2D.AddForce(transform.right * ThrowAngle * ThrowDir);
    }
    
    // Whenever Grenade is thrown, will have a random spin along the Z axis.
    protected virtual void Update() { transform.Rotate(Vector3.forward * Rand); }

    void OnCollisionEnter2D(Collision2D collision)
    {   // Updating ExplosionRadius here. It is updated even though OnDrawGizmo doesn't show properly.
        if (Magnified == true) { ExplosionRadius = ExplosionRadius * 2; }
        ExplosionDamage();  // Calling method from parent class.
        Instantiate(Explosion, new Vector3(transform.position.x, transform.position.y, transform.position.z-1), transform.rotation);
        Destroy(gameObject);
    }
}