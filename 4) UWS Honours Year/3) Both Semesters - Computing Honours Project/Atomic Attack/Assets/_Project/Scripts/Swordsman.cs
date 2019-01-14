using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Swordsman : Human {

    [Space(10), Header("[^ Child: Swordsman ]")]
    [SerializeField] float Delete;

    protected override void HP()
    {   // Overriding Friend's Class Health method
        base.HP();
        Debug.Log("change hp");
        // brackeys tower defense e04 and e05 for locking on and attacking
    }
}