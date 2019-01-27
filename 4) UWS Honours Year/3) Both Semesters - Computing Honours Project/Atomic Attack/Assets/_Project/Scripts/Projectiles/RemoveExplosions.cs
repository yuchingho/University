using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class RemoveExplosions : MonoBehaviour {

    void FixedUpdate()
    {
        Destroy(gameObject, 0.5f);
    }
}