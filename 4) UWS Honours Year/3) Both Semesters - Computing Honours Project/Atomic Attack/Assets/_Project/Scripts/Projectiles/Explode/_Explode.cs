using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class _Explode : MonoBehaviour {

    #pragma warning disable
    [SerializeField] string Tag = "Explode";
    public bool Magnified;
    public int Damage;
    [SerializeField] protected GameObject Explosion;
    [SerializeField] protected float ExplosionRadius;
    [SerializeField] protected float ExplosionScale;
    [SerializeField] protected int   ExplosionForce;

    protected virtual void ExplosionDamage()
    {   // Returns array of all colliders in ExplosionRadius.
        Collider2D[] colliders = Physics2D.OverlapCircleAll(transform.position, ExplosionRadius);
        foreach (Collider2D obj in colliders)
        {
            ManagerGame ManagerGame = GameObject.Find("Manager Game").GetComponent<ManagerGame>();
            HealthSystem HP = obj.gameObject.GetComponent<HealthSystem>();
            AI_Human Guy = obj.GetComponent<AI_Human>();
            if (Magnified == true)
            {   // ExplosionScale and Screenshake.
                Explosion.transform.localScale = new Vector2(ExplosionScale * 2, ExplosionScale * 2);
                //StartCoroutine(CameraShake.Shake(ExplosionRadius, ExplosionForce)); /* Duration, Magnitude */
                //Debug.Log("screenshake"); https://youtu.be/9A9yj8KnM8c 
                if (obj.name == "Castle Health")
                {
                    HP.DamageTaken(Damage * 2);
                    ManagerGame.CurrentScore += Damage * 10;
                    ManagerGame.CurrentGold  += Damage;
                }
                else if (obj.tag == "Enemy" || obj.tag == "Friend")
                {
                    if (Guy.Unshakeable == false)
                    {   // Adding push back from Explosion.
                        HP.DamageTaken(Damage * 2);
                        Guy.GetComponent<Rigidbody2D>().AddForce(transform.up    * ExplosionForce, ForceMode2D.Impulse);
                        Guy.GetComponent<Rigidbody2D>().AddForce(transform.right * ExplosionForce, ForceMode2D.Impulse);
                        Guy.MovementSpeed = 0;
                        Guy.Grounded = false;
                        Guy.GrabbedByMouse = true;
                        Guy.gameObject.layer = 10;
                    }   // Switching to Mouse Layer.
                    else { HP.DamageTaken(Damage * 2); }
                }
            }
            else if (obj.tag == "Enemy" || obj.tag == "Friend")
            {
                Explosion.transform.localScale = new Vector2(ExplosionScale, ExplosionScale);
                HP.DamageTaken(Damage);
            }
        }
    }
}