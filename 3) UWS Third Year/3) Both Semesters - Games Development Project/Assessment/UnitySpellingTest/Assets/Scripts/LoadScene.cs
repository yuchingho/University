using UnityEngine;
using UnityEngine.SceneManagement;

public class LoadScene : MonoBehaviour
{
    public string scene;

    public void ChangeScene()
    {
        SceneManager.LoadScene(scene);
    }
}