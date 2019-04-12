using UnityEngine;
using System.Collections;

public class Player_Controls : MonoBehaviour {
	public float speedForce = 10f;
	
	void Update () 
    {
		if (Input.GetKey (KeyCode.LeftArrow)) 
        {
            GetComponent<Rigidbody2D>().AddForce(Vector3.left * speedForce);
		} 
        else if (Input.GetKey (KeyCode.RightArrow)) 
        {
            GetComponent<Rigidbody2D>().AddForce(Vector3.right * speedForce);
		} 
	}
}