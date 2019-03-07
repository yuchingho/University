using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class _Explode : MonoBehaviour {

    public bool Magnified;

    public int Damage;
    [SerializeField] protected GameObject Explosion;
    [SerializeField] protected float ExplosionRadius;
    [SerializeField] protected float ExplosionScale;

    protected virtual void ExplosionDamage()
    {   // Returns array of all colliders in ExplosionRadius.
        Collider2D[] colliders = Physics2D.OverlapCircleAll(transform.position, ExplosionRadius);
        foreach (Collider2D obj in colliders)
        {
            HealthSystem HP = obj.gameObject.GetComponent<HealthSystem>();
            AI_Human Guy = obj.GetComponent<AI_Human>();
            if (Magnified == true)
            {   // Scaling ExplosionScale and adding Screenshake.
                Explosion.transform.localScale = new Vector2(ExplosionScale * 2, ExplosionScale * 2);
                //Debug.Log("screenshake");
                // https://youtu.be/9A9yj8KnM8c 

                if (obj.name == "Castle Health") { HP.DamageTaken(Damage * 2); }
                else if (obj.tag == "Enemy" || obj.tag == "Friend")
                {
                    if (Guy.Unshakeable == false)
                    {
                        HP.DamageTaken(Damage * 2);
                        //HP.DamageTaken(600);
                        Guy.GetComponent<Rigidbody2D>().AddForce(transform.up * 500);
                        Guy.GetComponent<Rigidbody2D>().AddForce(transform.right * 500);
                        // add force from explosion. make it better.
                        // always make it throw the Guy up in air. 
                        // A lot of the time, Guy slides across the ground and OnCollisionEnter doesn't properly register
                        // always thrown in same direction, make guys spread out like an actual explosion.
                        Guy.Grounded = false;
                        Guy.GrabbedByMouse = true;
                    }
                    else { HP.DamageTaken(Damage * 2); }
                }
            }
            else if (obj.tag == "Enemy")
            {
                Explosion.transform.localScale = new Vector2(ExplosionScale, ExplosionScale);
                HP.DamageTaken(Damage);
            }
        }
    }

    protected virtual void OnDrawGizmos()
    {
        Gizmos.color = Color.red;
        Gizmos.DrawWireSphere(transform.position, ExplosionRadius);
    }

}