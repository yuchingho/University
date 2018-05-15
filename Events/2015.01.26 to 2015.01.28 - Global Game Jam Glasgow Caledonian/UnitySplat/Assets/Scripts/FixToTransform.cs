using UnityEngine;
using System.Collections;

public class FixToTransform : MonoBehaviour {

    public Transform Target;

    // Update is called once per frame
    void Update()
    {
        transform.position = Target.position;
    }
}
