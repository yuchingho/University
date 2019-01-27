using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Bullet : MonoBehaviour {

    public float Speed;
    public int Damage;
    protected HealthSystem HealthSystem;
    protected Transform Target;

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
        Destroy(gameObject);

        // Don't need to differentiate with tags because already using Target.
        // Bullet will go only towards Target, through any other objects.
    }
}