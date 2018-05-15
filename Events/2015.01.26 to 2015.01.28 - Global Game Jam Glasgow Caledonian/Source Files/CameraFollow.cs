using UnityEngine;
using System.Collections;

public class CameraFollow : MonoBehaviour {

    public Transform Target;

	void Update () 
    {
        Vector3 newPos = transform.position;
        newPos.y = Target.position.y;
        transform.position = newPos;
    }
}
