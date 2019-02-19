using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class RemoveEffect : MonoBehaviour {

    [SerializeField] float Duration;

    void FixedUpdate()
    {
        Destroy(gameObject, Duration);
    }
}