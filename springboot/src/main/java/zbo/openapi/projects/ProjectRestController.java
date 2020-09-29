package zbo.openapi.projects;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import zbo.openapi.exceptions.OpenApiException;

import java.util.Collection;

@RestController
@RequestMapping("/api/project")
@Tag(name = "ProjectRestController")
public class ProjectRestController
{
    @Autowired
    private ProjectRepository repository;

    @GetMapping("/{id}")
    @Operation(summary = "Find project by id", description = "Only relevant for webapp")
    public Project findById(@Parameter(description = "The project id", required = true, example = "112233")
                            @PathVariable final Integer id)
    {
        return repository.findById(id).orElseThrow(() -> new OpenApiException("Project with id " + id + " not found"));
    }

    @GetMapping("/")
    @Operation(description = "Only relevant for webapp")
    public Collection<Project> findProjects()
    {
        return repository.findAll();
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Project updateProject(@Parameter(description = "The project id", required = true, example = "112233") @PathVariable("id") final Integer id, @RequestBody final Project project)
    {
        return repository.save(project);
    }
}
