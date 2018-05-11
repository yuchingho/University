using UnityEngine;

public class RockDelete : MonoBehaviour
{
    // Update is called once per frame
    void Update()
    {
        if (transform.position.y < -3f)
            Destroy(gameObject);
    }

    void OnCollisionEnter2D()
    {
        FindObjectOfType<RockSlow>().EndGame();
    }
}