package experiments.dataStructure.bst_generics_not_working.common;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(exclude = {"left", "right"})
public abstract class AbstractBSTNode<T extends Comparable<T>, N extends BSTNode<T, N>> implements BSTNode<T, N> {

    protected T value;
    protected N left;
    protected N right;
}
