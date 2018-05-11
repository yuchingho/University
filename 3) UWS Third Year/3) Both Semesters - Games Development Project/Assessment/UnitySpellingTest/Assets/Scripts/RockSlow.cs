using System.Collections;
using UnityEngine;
using UnityEngine.SceneManagement;

public class RockSlow : MonoBehaviour {

    public float slowDown = 5f;
    public string scene;
    
    public void EndGame()
    {
        StartCoroutine(RestartLevel());
    }

    IEnumerator RestartLevel()
    {
        Time.timeScale = 1f / slowDown;
        Time.fixedDeltaTime = Time.fixedDeltaTime / slowDown;

        // Time slowed down, will affect WaitForSeconds, thus is (1f), will == 5 seconds, due to slowDown.
        // Need to divide to make sure will waiting for "1 second"
        yield return new WaitForSeconds(1.5f/slowDown);
        // After one second

        Time.timeScale = 1f;
        Time.fixedDeltaTime = Time.fixedDeltaTime * slowDown;
        SceneManager.LoadScene(scene);
    }
}
