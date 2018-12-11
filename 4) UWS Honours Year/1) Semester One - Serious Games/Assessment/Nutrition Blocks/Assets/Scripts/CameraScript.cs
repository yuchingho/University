using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class CameraScript : MonoBehaviour {

    public float orthographicSize;  // 15
    public float aspect;            // 1.78

    void Start()
    {   // Scale GameObjects to any resolution.
        Camera.main.projectionMatrix = Matrix4x4.Ortho(-orthographicSize * aspect,
                                                        orthographicSize * aspect,
                                                       -orthographicSize, orthographicSize,
        GetComponent<Camera>().nearClipPlane, GetComponent<Camera>().farClipPlane);
    }
}