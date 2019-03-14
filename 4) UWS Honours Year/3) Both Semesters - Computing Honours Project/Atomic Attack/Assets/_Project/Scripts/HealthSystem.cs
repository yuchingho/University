using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class HealthSystem : MonoBehaviour {
    // Counter CollateralDamage;
    public float Health;
    public int CounterScore;
    public int GoldEarned;
    public int CounterE_Gunmen; 
    public int CounterE_Swordsmen;
    public int CounterFriends;
    public bool CounterAdded;
    public bool Deceased;

    public void DamageTaken(float Damage)
    {
        Health -= Damage;
        if (Health <= 0)
        {
            Deceased = true;
        }
    }
}