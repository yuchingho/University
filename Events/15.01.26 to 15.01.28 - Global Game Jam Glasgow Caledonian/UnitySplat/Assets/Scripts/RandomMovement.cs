using UnityEngine;
using System.Collections;

public class RandomMovement : MonoBehaviour {

    void FixedUpdate()
    {
        int random = Random.Range(0, 101);

        if (random < 2)
        {
            GetComponent<Rigidbody2D>().AddForce(Vector3.left * Random.Range(20, 100));
        }
        else if (random > 98)
        {
            GetComponent<Rigidbody2D>().AddForce(-Vector3.left * Random.Range(20, 100));
        }
    }
}
