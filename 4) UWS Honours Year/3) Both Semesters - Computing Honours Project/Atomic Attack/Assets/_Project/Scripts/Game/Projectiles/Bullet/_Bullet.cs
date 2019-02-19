using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class _Bullet : MonoBehaviour {

    protected Transform Target;
    protected HealthSystem HealthSystem;

    [Space( 18)]       public int Damage = 2;
    [SerializeField] protected int Speed = 15;
    [SerializeField] GameObject Explosion;

    public void Seek (Transform target)
    {
        Target = target;
    }

    protected virtual void Update() 
    {
		if (Target == null)
        {
            Destroy(gameObject);
            return;
        }
        Vector2 Direction = Target.position - transform.position;
        float DistanceThisFrame = Speed * Time.deltaTime;

        if (Direction.magnitude <= DistanceThisFrame)
        {   // Checking if hit, and not overshooting.
            HitTarget();
            return;
        }
        // If not hit Target, move towards Target.
        transform.Translate(Direction.normalized * DistanceThisFrame, Space.World);
	}

    protected virtual void HitTarget()
    {
        Target.GetComponent<HealthSystem>().DamageTaken(Damage);
        Instantiate(Explosion, transform.position, transform.rotation);
        Destroy(gameObject);
        // Don't need to differentiate with tags because already using Target.
        // Bullet will go only towards Target, through any other objects.
    }
}