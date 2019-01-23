using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class HealthSystem : MonoBehaviour {

    public float Health;
    // Use animator? play Die animation
    // coroutine and then destroy gameObject

    public void DamageTaken(float Damage)
    {
        Health -= Damage;
        if (Health <= 0)
        {
            Destroy(this.gameObject);
        }
    }
}