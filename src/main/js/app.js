const React = require('react');
const ReactDOM = require('react-dom');
const client = require('./client');

class App extends React.Component {

	constructor(props) {
		super(props);
		this.state = {properties: []};
	}

    fetchData(queryParam) {
        var that = this;
       client({method: 'GET', path: '/properties' + queryParam}).done(response => {
            that.setState({properties: response.entity});
       });
    }

	componentDidMount() {
		this.fetchData('');
	}

	render() {
		return (
			<PropertyList properties={this.state.properties} fetchData={(p) => this.fetchData(p) }/>
		)
	}
}

class PropertyList extends React.Component{
    constructor(props) {
        super(props);
        this.state = {
            realEstateType: '',
            minPrice: 0.0
        }
      this.updateFilterFn = this.updateFilter.bind(this);
    }

    updateFilter(filter, e)  {
        this.setState({realEstateType: e.target.value}, () => this.fetchFilteredData());
    }

    fetchFilteredData() {
        var queryParam = "?";
        var filterNames = Object.keys(this.state);
        filterNames.forEach((f) => {
            queryParam += f + "=" + this.state[f] + "&";
        })
        this.props.fetchData(queryParam);
    }

	render() {
		const properties = this.props.properties.map(property =>
			<Property key={property.listingId} property={property}/>
		);
		return (
			<table>
				<tbody>
					<tr>
						<th>
						    <select value={this.state.realEstateType} onChange={(e) => this.updateFilter('realEstateType', e)}>
						        {
						            ['', 'APARTMENT_RENT', 'APARTMENT_BUY', 'HOUSE_RENT', 'HOUSE_BUY'].map((option) => {
						                return <option value={option}>{option}</option>
						            })
						        }
						         </select></th>
						<th>Street</th>
						<th>House Number</th>
						<th>Postcode</th>
						<th>City</th>
						<th>Living Area</th>
						<th>Site Area</th>
						<th>Rental Price</th>
						<th>Sales Price</th>
						<th>Image URL</th>
					</tr>
					{properties}
				</tbody>
			</table>
		)
	}
}

class Property extends React.Component{
	render() {
		return (
			<tr>
				<td>{this.props.property.realEstateType}</td>
				<td>{this.props.property.street}</td>
				<td>{this.props.property.houseNumber}</td>
				<td>{this.props.property.postcode}</td>
				<td>{this.props.property.city}</td>
				<td>{this.props.property.livingArea}</td>
				<td>{this.props.property.siteArea}</td>
				<td>{this.props.property.rentalPrice}</td>
				<td>{this.props.property.salesPrice}</td>
				<td>{this.props.property.imageURL}</td>
			</tr>
		)
	}
}

ReactDOM.render(
	<App />,
	document.getElementById('react')
)