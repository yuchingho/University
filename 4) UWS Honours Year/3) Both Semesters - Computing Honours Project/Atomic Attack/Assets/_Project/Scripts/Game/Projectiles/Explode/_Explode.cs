using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class _Explode : MonoBehaviour {

    // _Explode only has a target with Grenade.
    protected HealthSystem HealthSystem;

    public int Damage;
    [SerializeField] protected GameObject Explosion;
    [SerializeField] protected int ExplosionRadius;


    protected virtual void Start()
    {
		
	}

    protected virtual void Update() 
    {
		
	}
}