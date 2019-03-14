using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class _Bullet : MonoBehaviour {

    protected Transform Target;

    public int Damage = 2;
    [SerializeField] protected int Speed = 15;
    [SerializeField] protected GameObject Explosion;




    public void Seek(Transform target)
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
        ManagerGame ManagerGame = GameObject.Find("Manager Game").GetComponent<ManagerGame>();
        Target.GetComponent<HealthSystem>().DamageTaken(Damage);
        Instantiate(Explosion, transform.position, transform.rotation);
        //Damaging Castle will add to Gold and Score.
        if (Target.gameObject.name == "Castle Health")
        {
            ManagerGame.CurrentScore += Damage * 100000;
            ManagerGame.CurrentGold  += Damage;
        }
        Destroy(gameObject);
        // Don't need to differentiate with tags because already using Target.
        // Bullet will go only towards Target, through any other objects.
    }
}