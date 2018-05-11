using UnityEngine;
using UnityEngine.SceneManagement;

public class FirePuzzleExit : MonoBehaviour
{

    public string LevelToLoad;

	// Use this for initialization
	void Start () {
		
	}
	
	// Update is called once per frame
	void Update () {
		
	}

    void OnTriggerEnter2D(Collider2D other)
    {
        if (other.name == "PlayerWizard")
        {
            Debug.Log("Win FirePuzzle");
            SceneManager.LoadScene(LevelToLoad);
        }
    }
}