using UnityEngine;
using System.Collections;

public class Clouds : MonoBehaviour {
	public Transform Player;
    public float Distance = 35;
    float speed = 5;
	float distance;
	
	void Update () 
    {
        transform.Translate(Vector3.up * Time.deltaTime * speed);
        if (transform.position.y < Player.position.y)
        {
            return;
        }

        distance = Mathf.Abs(transform.position.y - Player.position.y);

        if (distance > Distance)
		{
			Reload();
		}
	}
	void Reload() 
	{
		
		Vector3 newPosition = transform.position;
		newPosition.y = Player.position.y - distance;
		transform.position = newPosition;
		
	}
}
